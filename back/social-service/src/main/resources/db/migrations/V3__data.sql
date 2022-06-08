INSERT INTO public.t_friendrequest (id, sender, recipient, status, creation_date, last_update) VALUES ('eee7ad77-70ab-44d7-a6f9-d182e4e157cb', '2cff980e-886f-4680-a788-d96e211ecb23', 'b1e02a9a-0e68-4c3f-8d25-c9966a8ba0da', 'ACCEPTED', '14:08:26.666920 +01:00', '15:11:29.704466 +02:00');

INSERT INTO public.t_friendship (id, friend_a, friend_b, creation_date) VALUES ('1a9ed348-ce34-4a82-b4d5-1e7e31044d81', 'b1e02a9a-0e68-4c3f-8d25-c9966a8ba0da', '2cff980e-886f-4680-a788-d96e211ecb23', '15:11:29.728583 +02:00');

INSERT INTO public.t_message (id, friendship_id, author, content, created_at) VALUES ('0fe93c58-3799-43b1-b31a-c56bd295f981', '1a9ed348-ce34-4a82-b4d5-1e7e31044d81', 'b1e02a9a-0e68-4c3f-8d25-c9966a8ba0da', 'Hello ! Comment vas tu ?', '2022-06-08 13:12:31.745854 +00:00');
INSERT INTO public.t_message (id, friendship_id, author, content, created_at) VALUES ('b5d607c8-962a-45fc-8b6a-9aae5a71cb08', '1a9ed348-ce34-4a82-b4d5-1e7e31044d81', 'b1e02a9a-0e68-4c3f-8d25-c9966a8ba0da', 'Hello ! Comment vas tu ?', '2022-06-08 13:14:17.249641 +00:00');
INSERT INTO public.t_message (id, friendship_id, author, content, created_at) VALUES ('077baacd-ba6e-42ca-bb23-fa67a397ce04', '1a9ed348-ce34-4a82-b4d5-1e7e31044d81', 'b1e02a9a-0e68-4c3f-8d25-c9966a8ba0da', 'Faut pas respirer la compote, ça fait tousser', '2022-06-08 13:14:41.212319 +00:00');

