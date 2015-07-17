# Fast RSA Engine for jruby-openssl gem

this gem replaces the RSA signature and RSA ciphers from jruby-openssl by the must faster implementation of them. see [corner.squareup.com/2014/02/faster-rsa-jnagmp.html](https://corner.squareup.com/2014/02/faster-rsa-jnagmp.html)

but this works only for **darwin** and **linux-x84_64** platforms due to the library used from squareup.

the improvement in performance brings JRuby verify and decrypy using RSA close to MRI.

# TODO rewrite anything below here

# build and install in local maven-repo

```
./mvnw install
```

the actual gem can be also found in **./pkg**.

this gem can be used with gradle when adding ```mavenLocal()``` to the repository declarations.

# use the local gem the ruby way

first run

```
./mvnw package
```

to build the jar for the gem as well install the jar-dependencies of this gem locally.

add to your **Gemfile**

```
gem 'fast-rsa-engine', :path => 'path/to/here'
```

and run ```bundle install``` there.
