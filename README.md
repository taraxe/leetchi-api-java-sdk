# JAVA SDK for Leetchi API

Java SDK for Leetchi API

## Initialize the SDK

    Leetchi.config()
        .privateKey(pemFile)
        .partnerId("MyPartnerId")
        .baseUrl(Leetchi.PROD_URL)
        .password("my private key password");


## Create (or POST)

### generic way :

    Map<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Mark");
        map.put("LastName", "Zuckeberg");
        map.put("Email", "mark@leetchi.com");
        map.put("Nationality", "FR");
        map.put("PersonType", "NATURAL_PERSON");
        map.put("Tag", "Custom info from app");
    Map<String, ?> user = Leetchi.create(User.PATH, map)

Note : You can use any POJO and any api path instead

### with provided POJO :

    User user = Leetchi.create(User.newUser()
        .firstName("Mark")
        .lastName("Zuckeberg")
        .email("mark@leetchi.com")
        .nationality("FR")
        .personType("NATURAL_PERSON")
        .tag("Custom info from app"));

## Fetch (or GET)

### generic way :

    Map<String, ?> user = Leetchi.fetch(User.path(User.PATH, 15L), Map.class);
    CustomPojo beneficiary = Leetchi.fetch("beneficiaries/15", CustomPojo.class);

### with provided POJO :

    User user = User.fetch(15L);

## Update (or PUT)

### generic way :

    Map<String, String> map = new HashMap<String, String>();
        map.put("FirstName", "Mark");
        map.put("LastName", "Zuckeberg");
        map.put("Email", "mark@leetchi.com");
        map.put("Nationality", "FR");
        map.put("PersonType", "NATURAL_PERSON");
        map.put("Tag", "Custom info from app");
    Map<String, ?> user = Leetchi.put(User.path(User.PATH, 15L), map);

Note : You can use any POJO and any api path instead

### with provided POJO :

    User user = Leetchi.put(User.fetch(15L).email("newemail@gmail.com");

