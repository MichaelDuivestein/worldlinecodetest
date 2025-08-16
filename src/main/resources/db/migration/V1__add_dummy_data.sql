SET @alice_id = RANDOM_UUID(); -- Alice
SET @bob_id = RANDOM_UUID(); -- Bob
SET @charlie_id = RANDOM_UUID(); -- Charlie

INSERT INTO users (id, user_email, user_display_name) VALUES
    (@alice_id, 'alice@worldline.moc', 'Alice'),
    (@bob_id, 'bob@worldline.moc', 'Bob'),
    (@charlie_id, 'charlie@worldline.moc', 'Charlie');

SET @team_one_todo_list_id = RANDOM_UUID();
SET @charlies_personal_list_id = RANDOM_UUID();
SET @alice_and_bobs_list_id = RANDOM_UUID();

INSERT INTO task_list(id, created_by_user, list_name) VALUES
    (@team_one_todo_list_id, @alice_id, 'Team One TODO List'),
    (@charlies_personal_list_id, @alice_id, 'Charlie`s Personal List'),
    (@alice_and_bobs_list_id, @alice_id, 'Alice and Bob`s List');

SET @team_one_todo_alice_id = RANDOM_UUID();
SET @team_one_todo_bob_id = RANDOM_UUID();
SET @team_one_todo_charlie_id = RANDOM_UUID();
SET @charlies_personal_list_charlie_id = RANDOM_UUID();
SET @alice_and_bobs_personal_list_alice_id = RANDOM_UUID();
SET @alice_and_bobs_personal_list_bob_id = RANDOM_UUID();

INSERT INTO task_list_user (id, created_by_user, task_list_id, user_id, is_owner) VALUES
    (@team_one_todo_alice_id, @alice_id, @team_one_todo_list_id, @alice_id, TRUE),
    (@team_one_todo_bob_id, @alice_id, @team_one_todo_list_id, @bob_id, FALSE),
    (@team_one_todo_charlie_id, @alice_id, @team_one_todo_list_id, @charlie_id, FALSE),
    (@charlies_personal_list_charlie_id, @alice_id, @charlies_personal_list_id, @charlie_id, TRUE),
    (@alice_and_bobs_personal_list_alice_id, @alice_id, @alice_and_bobs_list_id, @alice_id, FALSE),
    (@alice_and_bobs_personal_list_bob_id, @alice_id, @alice_and_bobs_list_id, @bob_id, TRUE);

SET @task_item1_id = RANDOM_UUID();
SET @task_item2_id = RANDOM_UUID();
SET @task_item3_id = RANDOM_UUID();
SET @task_item4_id = RANDOM_UUID();
SET @task_item5_id = RANDOM_UUID();
SET @task_item6_id = RANDOM_UUID();
INSERT INTO task_list_item (id, created_by_user, task_list_id, task_name, task_description) VALUES
    (@task_item1_id, @charlie_id, @team_one_todo_list_id, 'Some Task One', 'Task one created for `Team One TODO List`'),
    (@task_item2_id, @alice_id, @team_one_todo_list_id, 'Some Task Two', 'Task two created for `Team One TODO List`'),
    (@task_item3_id, @alice_id, @team_one_todo_list_id, 'Some Task Three', 'Task three created for `Team One TODO List`'),
    (@task_item4_id, @bob_id, @team_one_todo_list_id, 'Some Task Four', 'Task four created for `Team One TODO List`'),
    (@task_item5_id, @charlie_id, @charlies_personal_list_id, 'Some Task Five', 'Task one created for `Charlie`s Personal List`'),
    (@task_item6_id, @alice_id, @charlies_personal_list_id, 'Some Task Six', 'Task two created for `Charlie`s Personal List`');

COMMIT;
