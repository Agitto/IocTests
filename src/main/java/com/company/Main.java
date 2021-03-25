package com.company;

import com.google.inject.*;

public class Main {

    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new MyModule());
        Steps steps = injector.getInstance(Steps.class);
        System.out.println(steps.getApiType());
    }
}

class MyModule implements Module {

    @Override
    public void configure(Binder binder) {
        if(1 == 0) {
            binder.bind(Api.class)
                    .to(RESTApi.class);
        } else {
            binder.bind(Api.class)
                    .to(RPCApi.class);
        }
    }
}

class Steps {
    private Api api;

    @Inject // говорит, что нужно подставить параметр, имплементация регистрируется в configure(..)
    public Steps(Api api) {
        this.api = api;
    }

    public String getApiType() {
        return api.getApiType();
    }
}

interface Api {
    String getApiType();
}

class RESTApi implements Api {

    @Override
    public String getApiType() {
        return "REST";
    }
}

class RPCApi implements Api {

    @Override
    public String getApiType() {
        return "RPC";
    }
}




