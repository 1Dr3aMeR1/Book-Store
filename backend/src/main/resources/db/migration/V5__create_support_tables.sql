create table support_tickets
(
    id          bigserial primary key,

    user_id     bigint not null,

    subject     varchar(255) not null,

    status      varchar(50) not null,

    created_at  timestamp not null,

    constraint fk_support_ticket_user
        foreign key (user_id)
            references users(id)
);

create table support_messages
(
    id          bigserial primary key,

    ticket_id   bigint not null,

    sender_id   bigint not null,

    message     text not null,

    created_at  timestamp not null,

    constraint fk_support_message_ticket
        foreign key (ticket_id)
            references support_tickets(id),

    constraint fk_support_message_sender
        foreign key (sender_id)
            references users(id)
);