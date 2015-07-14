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
