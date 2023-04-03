prod-build: 
	docker build -t botapeer-ecr-prod -f Dockerfile.prod .

up:
	docker compose up botapeer_core db

up-build:
	docker compose up --build botapeer_core db
	
up-debug:
	docker compose up  botapeer_core _debug db
	
up-debug-build:
	docker compose up --build botapeer_core_debug db	
	
test:
	./test.sh

test-debug:
	./test.debug.sh