package com.zxc.mybatis.builder;

import com.zxc.mybatis.session.Configuration;
import com.zxc.mybatis.type.TypeAliasRegistry;

public abstract class BaseBuilder {
    protected final Configuration configuration;

    protected final TypeAliasRegistry typeAliasRegistry;

    public BaseBuilder(Configuration configuration) {
        this.configuration = configuration;
        this.typeAliasRegistry = this.configuration.getTypeAliasRegistry();
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
