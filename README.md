# ER図
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
    l_activity_types ||--o{ activities : types
    s_activity_types ||--o{ activities : types
    labels ||--o{ activities : has
    plant_records ||--o{ posts : has
    plant_records ||--o{ labels : has
    plant_records ||--o{ comments : has
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
        string title
        boolean alive
        int status
        datetime end_date
        datetime created_at
        datetime updated_at
    }
    labels {
        int id PK
        int plant_record_id FK
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
        int plant_record_id FK
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
        int label_id FK
        int activity_record_id FK
        int l_activity_types
        int s_activity_types
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
        string name
    }
```
