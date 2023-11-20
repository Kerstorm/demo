package JavaApi1.demo.model;

public enum GuildRanks {
    GUILD_HEAD(1),
    GUILD_ADVISOR(2),
    GUILD_OFFICER(3),
    GUILD_SECRETARY(4),
    DEPUTY_GUILD_HEADS(5),
    SUPPLIER(6),
    GUNNER(7),
    MANAGER(8),
    PRIVATE(9),
    STUDENT(10);

    private final int id;

    public int getId() {
        return id;
    }

    GuildRanks(Integer id) {
        this.id = id;
    }
}

