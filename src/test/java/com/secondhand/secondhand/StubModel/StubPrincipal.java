package com.secondhand.secondhand.StubModel;

import java.security.Principal;

public class StubPrincipal implements Principal {
    String name;
    public StubPrincipal(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
