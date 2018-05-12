package net.kang.main.config;

import com.mongodb.ConnectionString;
import com.mongodb.DBRef;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.ReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.SimpleReactiveMongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefProxyHandler;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DbRefResolverCallback;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.core.mapping.MongoPersistentEntity;
import org.springframework.data.mongodb.core.mapping.MongoPersistentProperty;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import java.util.List;

@Configuration
@EnableReactiveMongoRepositories(basePackages = "net.kang.main.mongodb.repository")
public class ReactiveMongoConfig extends AbstractReactiveMongoConfiguration {
    @Value("${spring.data.mongodb.host}")
    private String mongoHost;

    @Value("${spring.data.mongodb.port}")
    private Integer mongoPort;

    @Value("${spring.data.mongodb.database}")
    private String mongoDatabase;

    @Value("${spring.data.mongodb.username}")
    private String userName;

    @Value("${spring.data.mongodb.password}")
    private String password;

    @Override
    public MongoClient reactiveMongoClient() {
        ConnectionString connectionString=new ConnectionString(String.format("mongodb://%s:%s@%s:%d/%s", userName, password, mongoHost, mongoPort, mongoDatabase));
        return MongoClients.create(connectionString);
    }

    @Override
    protected String getDatabaseName() {
        return mongoDatabase;
    }

    @Override
    public ReactiveMongoTemplate reactiveMongoTemplate() throws ClassNotFoundException{
        MappingMongoConverter converter=new MappingMongoConverter(new DbRefResolver() {
            @Override
            public Object resolveDbRef(MongoPersistentProperty property, DBRef dbref, DbRefResolverCallback callback,
                                       DbRefProxyHandler proxyHandler) {
                return null;
            }

            @Override
            public DBRef createDbRef(org.springframework.data.mongodb.core.mapping.DBRef annotation,
                                     MongoPersistentEntity<?> entity, Object id) {
                return null;
            }

            @Override
            public Document fetch(DBRef dbRef) {
                return null;
            }

            @Override
            public List<Document> bulkFetch(List<DBRef> dbRefs) {
                return null;
            }
        }, mongoMappingContext());
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return new ReactiveMongoTemplate(reactiveMongoDbFactory(), converter);
    }

    @Override
    public ReactiveMongoDatabaseFactory reactiveMongoDbFactory() {
        return new SimpleReactiveMongoDatabaseFactory(reactiveMongoClient(), getDatabaseName());
    }

    @Override
    public MongoMappingContext mongoMappingContext() throws ClassNotFoundException {
        return super.mongoMappingContext();
    }

}
