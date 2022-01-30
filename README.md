# FxProperty
## Generated JavaFX property accessors
![](docs/fxproperty-demo.gif)
### Original
```java
class IntegerPropertyTest {
    @FxProperty
    IntegerProperty field = new SimpleIntegerProperty(this, "field");
}
```

#### Transformed
```java
class IntegerPropertyTest {
    @FxProperty
    IntegerProperty field = new SimpleIntegerProperty(this, "field");
    
    public Integer getField() {
        return field.getValue();
    }
    
    public IntegerProperty fieldProperty() {
        return field;
    }
    
    public void setField(Integer field) {
        this.field.set(field);
    }
}
```
