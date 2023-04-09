# プロダクション用のビルド
prod-build:
	docker build -t botapeer-ecr-prod -f Dockerfile.prod .

# アプリケーションを構成する
up:
	docker compose up

# アプリケーションをビルドして構成する
up-build:
	docker compose up --build

# アプリケーションをデバッグモードで構成する
up-debug:
	docker compose -f docker-compose.debug.yml up

# アプリケーションをデバッグモードでビルドして構成する
up-debug-build:
	docker compose -f docker-compose.debug.yml up --build

# アプリケーションをテスト用のデータベースで構成する
up-testdb:
	docker compose -f docker-compose.test.yml up

# アプリケーションをテスト用のデータベースでビルドして構成する
up-testdb-build:
	docker compose -f docker-compose.test.yml up --build

# アプリケーションをテスト用のデータベースでデバッグビルドで構成する
up-testdb-debug:
	docker compose -f docker-compose.test.debug.yml up

# アプリケーションをテスト用のデータベースでデバッグビルドでビルドして構成する
up-testdb-debug-build:
	docker compose -f docker-compose.test.debug.yml up --build

# テストを実行する
test:
	./test.sh

# テストをデバッグモードで実行する
test-debug:
	./test.debug.sh
