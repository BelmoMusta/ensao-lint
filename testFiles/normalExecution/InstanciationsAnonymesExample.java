interface MyInterface {
    void doSomething();
}

class InstanciationsAnonymesExample {
    void testMethod() {
        MyInterface anon = new MyInterface() {
            @Override
            public void doSomething() {
                System.out.println("doing something");
            }
        };
    }
}
