create table if not exists public.content
(
    title               text,
    type                text,
    genres              text,
    release_year        integer,
    imdb_id             text,
    imdb_average_rating real,
    imdb_vote_count     integer,
    available_countries text,
    id                  bigserial
);

alter table public.content
    owner to postgres;

