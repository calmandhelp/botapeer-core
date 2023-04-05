# 概要
Spring Bootで開発されたbotapeerのバックエンドAPIです。

## アプリケーションアーキテクチャ
基本的にはクリーンアーキテクチャの思想を参考に開発されたプロジェクトです。
![68747470733a2f2f71696974612d696d6167652d73746f72652e73332e616d617a6f6e6177732e636f6d2f302f3239333336382f37636531666231302d353034652d313665302d383933302d3237386238613766393432642e6a706567](https://user-images.githubusercontent.com/39892315/229960337-019f959f-18c3-4233-bd21-9b08b568f321.jpg)

### 黄色い部分
Enterpisse Business Rulesにはビジネスロジックが書かれています。このプロジェクトではdomain層にある各サービスやドメインモデルや値オブジェクトがそれにあたります。  
### 赤い部分
Application Business Rulesにはユースケース（アプリケーションロジック）が書かれています。このプロジェクトではusecase層がそれにあたります。
usecase層はdomain層に依存し、ワークフローやビジネスロジックを実行する役割を持ちます。
### 緑の部分
Interfaces Adaptersは外部との接続を繋ぐ役割を持ちます。例えば、repositoryのインターフェース、そしてそれを実装したinfrastructureのコードによってDBに接続するためのゲートウェイの役割を待たすことができます。他にこのプロジェクトで利用されている層はadapter層にて、S3に画像をアップロードするのに利用されています。主に外部への依存関係を切り離すための役割を持たせています。
### 青の部分
Frameworks & Driversはアプリケーション外部と実際に接続するための役割をもちます。その名の通り、フレームワークやドライバです。

上記のような認識として、開発をすすめていきました。

ディレクトリ構造をなるべくシンプルにすると以下のようになります。
```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── botapeer
│   │   │           ├── adapter
│   │   │           ├── config
│   │   │           ├── constants
│   │   │           ├── controller
│   │   │           │   └── payload
│   │   │           │       └── label
│   │   │           ├── domain
│   │   │           │   ├── model
│   │   │           │   │   ├── label
│   │   │           │   │   ├── like
│   │   │           │   │   ├── place
│   │   │           │   │   ├── plantRecord
│   │   │           │   │   ├── post
│   │   │           │   │   ├── text
│   │   │           │   │   └── user
│   │   │           │   ├── repository
│   │   │           │   ├── adapter
│   │   │           │   └── service
│   │   │           │       └── interfaces
│   │   │           ├── exception
│   │   │           ├── infrastructure
│   │   │           │   ├── entity
│   │   │           │   ├── mapper
│   │   │           │   └── repository
│   │   │           │       └── dto
│   │   │           │           ├── like
│   │   │           │           ├── place
│   │   │           │           ├── plantRecord
│   │   │           │           ├── post
│   │   │           │           └── user
│   │   │           ├── security
│   │   │           ├── usecase
│   │   │           │   ├── dto
│   │   │           │   │   ├── place
│   │   │           │   │   ├── plantRecord
│   │   │           │   │   ├── post
│   │   │           │   │   └── user
│   │   │           │   └── interfaces
│   │   │           └── util

```

## バリデーション
Bean Validationを利用しています。
Open APIリポジトリで定義され、openapi-generatorを通して自動生成されたモデルを利用しているため、バリデーションはコントローラ層でまず行われます。  
そのため、パラメータの異常値はバリデーションエラーとなり、例外が投げられます。  
また、ビジネスロジックを実行するサービス層、ユースケース層でもそれぞれバリデーションを行っています。  
単体テストではバリデーションも含めて、異常系のテストを行なっています。  

## 単体テスト
JUnit5、モックライブラリにはMockitoを利用して行います。
Mavenのテストディレクトリ、src/test/javaにテスト用のファイルを置いています。  
それぞれのディレクトリとクラスはsrc/main/javaに対応しています。  
テストコードを実行するにはmake upコマンドでDockerコンテナを立ち上げた状態で、make testとすることでテストが実行されます。  
makefileにはビルドやコンテナ実行用のコマンドを記述しているので、そのほかのコマンドもご参照ください。  

## データベース
MySQLを利用しています。開発環境ではdocker-composeで定義されたDBがサービスとして立ち上がります。  
infrastructure層でDBのアクセスをする処理を行います。OR MapperはMyBatisです。  

### ER図
マーメイドを利用して設計しています。単に図を書きたい時はサクッと書けて便利です。  
エンティティの中には設計しただけで実装されていないものも含まれています。

```mermaid
erDiagram
    users ||--o{ follows : follows
    users ||--o{ plant_records : creates
    users ||--o{ likes : likes
    users ||--o{ goodjobs : goodjobs
    users ||--o{ comments : posts
    users ||--o{ activity_records : creates
    posts ||--o{ likes : has
    posts ||--o{ comments : has
    comments ||--o{ likes : has
    comments ||--o{ comments : has
    l_activity_types ||--o{ s_activity_types : has
    s_activity_types ||--o{ activities : types
    plant_records ||--o{ activities : has     
    plant_records ||--o{ posts : has
    places ||--o{ plant_records : has
    activity_records ||--o{ goodjobs : has
    activity_records ||--o{ activities : has
    activity_records ||--o{ comments : has
    users {
        int id PK
        string name
        string email
        string password
        string description
        int status
        datetime created_at
        datetime updated_at 
    }
    follows {
        int id PK
        int followee_id FK
        int follower_id FK
        datetime created_at
        datetime updated_at
    }
    plant_records {
        int id PK
        int user_id FK
        int place_id FK
        string title
        boolean alive
        int status
        datetime end_date
        datetime created_at
        datetime updated_at 
    }
    places {
        int id PK
        string name
        datetime created_at
        datetime updated_at
    }
    posts {
        int id PK
        int plant_record_id FK
        string title
        string article
        string image_url
        int status
        datetime created_at
        datetime updated_at
    }
    likes {
        int id PK
        int user_id FK
        int post_id FK
        int comment_id FK
        datetime created_at
        datetime updated_at
    }
    comments {
        int id PK
        int post_id FK
        int user_id FK
        int comment_id FK
        string content
        int status
        datetime created_at
        datetime updated_at
    }
    activity_records {
        int id PK
        int user_id FK
        string image_url 
        string article 
        int status
        datetime created_at
        datetime updated_at
    }
    activities {
        int id PK
        int plant_record_id FK
        int activity_record_id FK
        int s_activity_types_id FK
        int status     
        datetime created_at
        datetime updated_at
    }
    goodjobs {
        int id PK
        int received_user_id FK
        int send_user_id FK
        int activity_record_id FK     
        datetime created_at
        datetime updated_at
    }
    l_activity_types {
        int id PK
        string name
    }
    s_activity_types {
        int id PK
        int l_activity_type_id FK
        string name
    }
```

## 認証
認証はSpring Securityを使用しています。  
config/SecurityConfig.javaでセキュリティに関する設定を行なっています。
/apiがAPIのルートパスになります。ルートパス以降のGETでの呼び出しは全て許可しており、それ以外のAPIは認証時のみ利用可能です。  
ただし、例外的に/api/authだけはユーザー作成やログインのために、POSTを呼び出す必要があるため認証をせずに呼び出すことができます。
