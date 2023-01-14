/**
 * To test our rule i will write an Enum non valide according to my rule
 * an enum non valid contains at least one member that have a char in lowercase
 * **/

public enum TestingEnumElementName {

    GI3,
    GI4,
    GI5,
    ensao,
    ump
}

/**
 * To make the pervious enum valid
 * i will write all members in uppercase
 * **/

public enum TestingEnumElementNameValid {

    GI3,
    GI4,
    GI5,
    ENSAO,
    UMP
}
