/*create table public.authors
(
    id          serial
        primary key,
    description text,
    first_name  varchar(255),
    last_name   varchar(255),
    photo       varchar(255),
    slug        varchar(255)
);

create table public.book2user_type
(
    id   serial
        primary key,
    code varchar(255) not null,
    name varchar(255) not null
);

create table public.book_file_type
(
    id          serial
        primary key,
    description text,
    name        varchar(255) not null
);

create table public.books
(
    id               serial
        primary key,
    description      text,
    image            varchar(255),
    in_cart_amount   bigint,
    is_bestseller    integer,
    postponed_amount bigint,
    discount         double precision,
    price            integer,
    pub_date         date,
    purchase_amount  bigint,
    slug             varchar(255),
    title            varchar(255),
    author_id        integer
        constraint fkfjixh2vym2cvfj3ufxj91jem7
            references public.authors
);

create table public.book2author
(
    id         serial
        primary key,
    sort_index integer default 0 not null,
    author_id  integer
        constraint fk3hyom3yo5q6nfo9ytqofqil37
            references public.authors,
    book_id    integer
        constraint fk8joa8tens71ol1st4fd9hssph
            references public.books
);

create table public.book_file
(
    id      serial
        primary key,
    hash    varchar(255),
    path    varchar(255),
    type_id integer,
    book_id integer
        constraint fkg7meofferi2glg6uwj2yhfmp9
            references public.books
);

create table public.document
(
    id         serial
        primary key,
    slug       varchar(255)      not null,
    sort_index integer default 0 not null,
    text       text              not null,
    title      varchar(255)      not null
);

create table public.faq
(
    id         serial
        primary key,
    answer     text              not null,
    question   varchar(255)      not null,
    sort_index integer default 0 not null
);

create table public.genre
(
    id        serial
        primary key,
    name      varchar(255) not null,
    slug      varchar(255) not null,
    parent_id integer
        constraint fk19g7t6b636h69dufjf98ofc0x
            references public.genre
);

create table public.book2genre
(
    id       serial
        primary key,
    book_id  integer
        constraint fkceqa97sssb6d5xg7n8jtt5gqb
            references public.books,
    genre_id integer
        constraint fkkdc8kcprnkl4ygg2f0v9588wi
            references public.genre
);

create table public.jwt_blacklist
(
    id          bigserial
        primary key,
    closed_date timestamp,
    token       varchar(255)
        constraint uk_cyoqfswqavc5tacxw1286l3nb
            unique
);

create table public.rate
(
    id      serial
        primary key,
    rate    integer,
    book_id integer
        constraint fkgpkn703by1bag07t3yuynlxi3
            references public.books
);

create table public.tags
(
    id   serial
        primary key,
    name varchar(255)
);

create table public.book2tag
(
    id      serial
        primary key,
    book_id integer
        constraint fkkshb2gce4ettp6ehidjy86u5c
            references public.books,
    tag_id  integer
        constraint fkbha16g6maqt9emet4pt8qq9
            references public.tags
);

create table public.users
(
    id       serial
        primary key,
    email    varchar(255),
    name     varchar(255),
    password varchar(255),
    phone    varchar(255)
);

create table public.users_entity
(
    id       serial
        primary key,
    balance  integer      not null,
    hash     varchar(255) not null,
    name     varchar(255) not null,
    reg_time timestamp    not null
);

create table public.balance_transaction
(
    id          serial
        primary key,
    description text              not null,
    time        timestamp         not null,
    value       integer default 0 not null,
    book_id     integer
        constraint fke2aw1i7lhjey4rskq9qhed9qs
            references public.books,
    user_id     integer
        constraint fkqax956fvtc8s96a358hadnm62
            references public.users_entity
);

create table public.book2user
(
    id      serial
        primary key,
    time    timestamp not null,
    type_id integer   not null,
    book_id integer
        constraint fkd80i5quc7o0xb1d1k8ppqmtsu
            references public.books,
    user_id integer
        constraint fkbouesn4a7egqv81hdd7m9npt5
            references public.users_entity
);

create table public.book_review
(
    id      serial
        primary key,
    text    text      not null,
    time    timestamp not null,
    book_id integer
        constraint fkgme5o68sylsmw5hw0ys2x8iw8
            references public.books,
    user_id integer
        constraint fk671cwo1mxtomd3gcshr88y0p9
            references public.users_entity
);

create table public.book_review_like
(
    id        serial
        primary key,
    review_id integer   not null,
    time      timestamp not null,
    value     smallint  not null,
    user_id   integer
        constraint fkm4t2nq15x35a615ud1hhdtsdc
            references public.users_entity
);

create table public.file_download
(
    id      serial
        primary key,
    count   integer default 1 not null,
    book_id integer
        constraint fkswtliibr0mrua60wbmecwlfyo
            references public.books,
    user_id integer
        constraint fkkx28g8lx6wmlwln1kdn2r9vor
            references public.users_entity
);

create table public.message
(
    id      serial
        primary key,
    email   varchar(255),
    name    varchar(255),
    subject varchar(255) not null,
    text    text         not null,
    time    timestamp    not null,
    user_id integer
        constraint fks3dwoskuycwsq5dyvlj7in6mr
            references public.users_entity
);

create table public.user_contact
(
    id          serial
        primary key,
    approved    smallint     not null,
    code        varchar(255) not null,
    code_time   timestamp,
    code_trails integer,
    contact     varchar(255) not null,
    type        integer,
    user_id     integer
        constraint fknjr99y54g58jfdl4dqg4k7q5v
            references public.users_entity
);
*/

insert into users (id,email,name,password,phone) values(1,'qwe@mail.ru','qwe','$2a$10$M6dOunxEPsRmB/LgkiXDouLt9iFfZTKvpdwUMR4Xwp3VzNsNsekfK','+7 (123) 123-12-31');

insert into authors (id, first_name, last_name, slug, photo, description) values (1, 'Friedrich', 'Baruch', 'author-143-bwm', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc.');
insert into authors (id, first_name, last_name, slug, photo, description) values (2, 'Ragnar', 'Dugmore', 'author-386-fju', 'http://dummyimage.com/400x625.png/cc0000/ffffff', 'In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat.');
insert into authors (id, first_name, last_name, slug, photo, description) values (3, 'Jsandye', 'Peyto', 'author-504-uui', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio.');
insert into authors (id, first_name, last_name, slug, photo, description) values (4, 'Reginauld', 'Simmonett', 'author-085-iru', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque.');
insert into authors (id, first_name, last_name, slug, photo, description) values (5, 'Melba', 'Hembling', 'author-290-yek', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa.');
insert into authors (id, first_name, last_name, slug, photo, description) values (6, 'Barde', 'Lory', 'author-821-hfo', 'http://dummyimage.com/400x625.png/ff4444/ffffff', 'Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus.');
insert into authors (id, first_name, last_name, slug, photo, description) values (7, 'Boyd', 'Munns', 'author-944-zxk', 'http://dummyimage.com/400x625.png/cc0000/ffffff', 'In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede.');
insert into authors (id, first_name, last_name, slug, photo, description) values (8, 'Ginnie', 'Merioth', 'author-418-ybc', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl. Duis bibendum, felis sed interdum venenatis, turpis enim blandit mi, in porttitor pede justo eu massa. Donec dapibus. Duis at velit eu est congue elementum. In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus. Aenean fermentum.');
insert into authors (id, first_name, last_name, slug, photo, description) values (9, 'Martica', 'Purvess', 'author-241-lhg', 'http://dummyimage.com/400x625.png/cc0000/ffffff', 'Nulla justo. Aliquam quis turpis eget elit sodales scelerisque. Mauris sit amet eros. Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst.');
insert into authors (id, first_name, last_name, slug, photo, description) values (10, 'Lyndsey', 'Betchley', 'author-865-fnr', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst. Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem. Integer tincidunt ante vel ipsum. Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat.');
insert into authors (id, first_name, last_name, slug, photo, description) values (11, 'Maryrose', 'Pleaden', 'author-536-iji', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst. Etiam faucibus cursus urna. Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy.');
insert into authors (id, first_name, last_name, slug, photo, description) values (12, 'Willy', 'Langcastle', 'author-262-fzh', 'http://dummyimage.com/400x625.png/ff4444/ffffff', 'Duis bibendum. Morbi non quam nec dui luctus rutrum. Nulla tellus. In sagittis dui vel nisl. Duis ac nibh. Fusce lacus purus, aliquet at, feugiat non, pretium quis, lectus. Suspendisse potenti. In eleifend quam a odio. In hac habitasse platea dictumst. Maecenas ut massa quis augue luctus tincidunt. Nulla mollis molestie lorem. Quisque ut erat. Curabitur gravida nisi at nibh. In hac habitasse platea dictumst.');
insert into authors (id, first_name, last_name, slug, photo, description) values (13, 'Carly', 'Georgievski', 'author-807-zet', 'http://dummyimage.com/400x625.png/ff4444/ffffff', 'Morbi vel lectus in quam fringilla rhoncus. Mauris enim leo, rhoncus sed, vestibulum sit amet, cursus id, turpis. Integer aliquet, massa id lobortis convallis, tortor risus dapibus augue, vel accumsan tellus nisi eu orci. Mauris lacinia sapien quis libero. Nullam sit amet turpis elementum ligula vehicula consequat. Morbi a ipsum. Integer a nibh. In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris viverra diam vitae quam.');
insert into authors (id, first_name, last_name, slug, photo, description) values (14, 'Kacie', 'Robertet', 'author-420-hsr', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui.');
insert into authors (id, first_name, last_name, slug, photo, description) values (15, 'Lorine', 'Stein', 'author-245-uzu', 'http://dummyimage.com/400x625.png/cc0000/ffffff', 'Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor. Duis mattis egestas metus. Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh. Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est. Donec odio justo, sollicitudin ut, suscipit a, feugiat et, eros. Vestibulum ac est lacinia nisi venenatis tristique. Fusce congue, diam id ornare imperdiet, sapien urna pretium nisl, ut volutpat sapien arcu sed augue. Aliquam erat volutpat. In congue. Etiam justo.');
insert into authors (id, first_name, last_name, slug, photo, description) values (16, 'Glynda', 'Vaan', 'author-022-cra', 'http://dummyimage.com/400x625.png/ff4444/ffffff', 'Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus. Pellentesque eget nunc. Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis. Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat. Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi. Nulla ac enim. In tempor, turpis nec euismod scelerisque, quam turpis adipiscing lorem, vitae mattis nibh ligula nec sem. Duis aliquam convallis nunc. Proin at turpis a pede posuere nonummy. Integer non velit. Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue.');
insert into authors (id, first_name, last_name, slug, photo, description) values (17, 'Aleda', 'Alsford', 'author-001-ehr', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque. Aenean auctor gravida sem. Praesent id massa id nisl venenatis lacinia.');
insert into authors (id, first_name, last_name, slug, photo, description) values (18, 'Alyss', 'Burleton', 'author-913-avn', 'http://dummyimage.com/400x625.png/5fa2dd/ffffff', 'Praesent blandit lacinia erat. Vestibulum sed magna at nunc commodo placerat. Praesent blandit. Nam nulla. Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus. In est risus, auctor sed, tristique in, tempus sit amet, sem. Fusce consequat. Nulla nisl. Nunc nisl.');
insert into authors (id, first_name, last_name, slug, photo, description) values (19, 'Mycah', 'Gammidge', 'author-930-tlq', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus. Curabitur at ipsum ac tellus semper interdum. Mauris ullamcorper purus sit amet nulla. Quisque arcu libero, rutrum ac, lobortis vel, dapibus at, diam. Nam tristique tortor eu pede.');
insert into authors (id, first_name, last_name, slug, photo, description) values (20, 'Moore', 'Towle', 'author-635-bvg', 'http://dummyimage.com/400x625.png/dddddd/000000', 'Ut tellus. Nulla ut erat id mauris vulputate elementum. Nullam varius. Nulla facilisi. Cras non velit nec nisi vulputate nonummy. Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat. Quisque erat eros, viverra eget, congue eget, semper rutrum, nulla. Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio.');
insert into authors (id, first_name, last_name, slug, photo, description) values (21, 'Tobiah', 'Glide', 'author-489-wnx', 'http://dummyimage.com/400x625.png/ff4444/ffffff', 'Nunc purus. Phasellus in felis. Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius. Integer ac leo. Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.');

insert into books (id, pub_date, is_bestseller, slug, title, image, description, price, discount, author_id, in_cart_amount, postponed_amount, purchase_amount) values (1, '2015-06-22', 0, 'book-tni-417', 'Hearts Divided', 'http://dummyimage.com/770x562.png/cc0000/ffffff', 'Donec posuere metus vitae ipsum. Aliquam non mauris. Morbi non lectus. Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis. Fusce posuere felis sed lacus.', 1298, 0.42, 4, 33, 136, 105);
insert into books (id, pub_date, is_bestseller, slug, title, image, description, price, discount, author_id, in_cart_amount, postponed_amount, purchase_amount) values (2, '2002-02-17', 0, 'book-qjy-110', 'Barbie: A Perfect Christmas', 'http://dummyimage.com/324x462.png/ff4444/ffffff', 'Donec diam neque, vestibulum eget, vulputate ut, ultrices vel, augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec pharetra, magna vestibulum aliquet ultrices, erat tortor sollicitudin mi, sit amet lobortis sapien sapien non mi. Integer ac neque. Duis bibendum. Morbi non quam nec dui luctus rutrum.', 683, 0.14, 13, 89, 82, 49);
insert into books (id, pub_date, is_bestseller, slug, title, image, description, price, discount, author_id, in_cart_amount, postponed_amount, purchase_amount) values (3, '2018-08-18', 1, 'book-kgq-856', 'Prince of Pennsylvania, The', 'http://dummyimage.com/799x569.png/5fa2dd/ffffff', 'Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet. Maecenas leo odio, condimentum id, luctus nec, molestie sed, justo. Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.', 2361, 0.28, 13, 132, 90, 148);
