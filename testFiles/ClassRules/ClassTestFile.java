package classRules;

public class ClassTestFile {
        /**
         * In this class i will test several rules
         * first of all : attributeName
         * here are some attributs that dont start with lowercase
         * so according to my rule it will throw an error
         * **/

        private String AttributeFalsyN1;
        private  int AttributeFalsyN2;

        /**
         * This attribute its not falsy according to rule : attributeName
         * because the rule take into conideration non final attribute
         * but its falsy according to the rule ClassConstantsName
         * **/

        private final long AttributeFalsyN3;

        /**
         * Now im going to write some valid attributs according
         * to my rules
         * **/

        private String attributevalidN1;
        private  int attributeValidN2;

}