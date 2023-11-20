CREATE TABLE GUILDS
(
    GUILD_ID INT AUTO_INCREMENT     PRIMARY KEY,
    TITLE             NVARCHAR(250) NOT NULL,
    CREATE_DATE       DATE          NOT NULL,
    COUNT_MEMBERS     INT           NOT NULL,
    PVP               BOOLEAN       NOT NULL
);

CREATE TABLE MEMBERS
(
    MEMBER_ID       INT AUTO_INCREMENT PRIMARY KEY,
    GUILD_ID        INT REFERENCES GUILDS,
    NICKNAME        NVARCHAR(250) NOT NULL,
    REALNAME        NVARCHAR(250) NOT NULL,
    DATE_OF_JOIN    DATE         NOT NULL,
    RANKS           INT           NOT NULL,
    IS_ACTIVE       BOOLEAN       NOT NULL
);
