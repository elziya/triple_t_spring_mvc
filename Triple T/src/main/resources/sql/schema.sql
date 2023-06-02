create table account(
    id serial primary key,
    first_name varchar(150),
    last_name varchar(150),
    email varchar(150) not null,
    password_hash varchar not null,
    role varchar(50) not null
);

create table project(
    id serial primary key,
    name varchar(50) not null,
    start_date timestamp not null,
    end_date timestamp not null,
    duration integer default 0,
    is_done boolean default false,
    account_id integer not null,
    foreign key (account_id) references account(id)
);

create table task(
    id serial primary key,
    name varchar(50),
    duration integer default 0,
    project_id integer not null,
    foreign key (project_id) references project(id)
);

create table tag(
    id serial primary key,
    account_id integer not null,
    foreign key (account_id) references account(id),
    tag_name varchar(40) not null
);

create table task_tag(
    task_id integer not null,
    foreign key (task_id) references task(id),
    tag_id integer not null,
    foreign key (tag_id) references tag(id),
    primary key(task_id, tag_id)
);

create table file_info(
      id serial primary key,
      size bigint not null,
      content_type varchar(200) not null,
      orig_name varchar(200) not null,
      storage_name varchar(200) not null,
      task_id integer not null,
      foreign key (task_id) references task(id)
);
