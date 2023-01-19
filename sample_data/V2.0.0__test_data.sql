USE botapeer_db;

INSERT INTO users(name, email, password, description) values("okuda", "sts868dti@dti.ne.jp", "$2a$12$GdY.dZn7wjfLnlqURlaacObGfp9.i0UJ7EQaYbNr4AtAuxVi5nY12", "初めまして！園芸初心者ですがこれから頑張って投稿しようと思います。コメントもお気軽にどうぞ！");
INSERT INTO users(name, email, password, description) values("oosaki", "masaru1972@example.com", "$2a$12$GdY.dZn7wjfLnlqURlaacObGfp9.i0UJ7EQaYbNr4AtAuxVi5nY12", "自宅で野菜を栽培して10年になります。最近畑を購入しました。");
INSERT INTO users(name, email, password, description) values("okumura", "rmko20050413@web.ad.jp", "$2a$12$GdY.dZn7wjfLnlqURlaacObGfp9.i0UJ7EQaYbNr4AtAuxVi5nY12",  "農学部3年生。ゼミでは細菌の研究をしています。植物病理学に関心があります。");
INSERT INTO users(name, email, password, description) values("takeuti", "yumi3119@dti.ad.jp", "$2a$12$GdY.dZn7wjfLnlqURlaacObGfp9.i0UJ7EQaYbNr4AtAuxVi5nY12", "自宅で野菜を栽培して10年になります。最近畑を購入しました。");
INSERT INTO users(name, email, password, description) values("narumi", "utiumi81@example.jp", "$2a$12$GdY.dZn7wjfLnlqURlaacObGfp9.i0UJ7EQaYbNr4AtAuxVi5nY12", "自宅で野菜を栽培して10年になります。最近畑を購入しました。");

INSERT INTO plants(user_id, title, description, alive) values(1, "ひまわり", "綺麗なひまわりのお花", 1);
INSERT INTO plants(user_id, title, description, alive) values(2, "ネモフィラ", "ひたち海浜公園でみかけました。", 1);
INSERT INTO plants(user_id, title, description, alive) values(3, "杜若", "公園の池で発見！", 1);
INSERT INTO plants(user_id, title, description, alive) values(4, "ラナンキュラス", "青山フラワーマーケットで買いました", 1);
INSERT INTO plants(user_id, title, description, alive) values(5, "プチトマト", "庭で育てました。", 1);