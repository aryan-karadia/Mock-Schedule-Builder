   @Before
    public void setUp() {
        animal1 = new Animal(1, "Dog", "Scout", DIURNAL, careNeeded, feedingSchedule, 12);
        treatment1 = new Treatment(2, 12);
        schedule1 = new Schedule(animals[]);
    }

    // Animal tests
    @Test
    public void testGetAnimalId() {
        int id = animal1.getId();
        assertEquals("Animal ID should be 1, but it's not", 1, id);
    }

    @Test
    public void testGetType() {
        String type = animal1.getType();
        assertEquals("Animal type should be 'Dog', but it's not", "Dog", type);
    }

    @Test
    public void testGetName() {
        String name = animal1.getName();
        assertEquals("Animal name should be 'Scout', but it's not", "Scout", name);
    }

    @Test
    public void testGetActivityPattern() {
        ActivityPattern activityPattern = animal1.getActivityPattern();
        assertEquals("Animal activity pattern should be DIURNAL, but it's not", ActivityPattern.DIURNAL, activityPattern);
    }

    @Test
    public void testGetCareNeeded() {
        String careNeeded = animal1.getCareNeeded();
        assertEquals("Animal care needed should be 'careNeeded', but it's not", "careNeeded", careNeeded);
    }

    @Test
    public void testGetFeedingSchedule() {
        String feedingSchedule = animal1.getFeedingSchedule();
        assertEquals("Animal feeding schedule should be 'feedingSchedule', but it's not", "feedingSchedule", feedingSchedule);
    }

    @Test
    public void testGetAge() {
        int age = animal1.getAge();
        assertEquals("Animal age should be 12, but it's not", 12, age);
    }
