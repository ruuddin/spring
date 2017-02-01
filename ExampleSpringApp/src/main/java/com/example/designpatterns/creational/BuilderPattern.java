
package com.example.designpatterns.creational;

public class BuilderPattern {

    public static class Builder {
        private String bread;
        private String dressing;
        private String meat;

        public Builder(){}
        
        public BuilderPattern build() {
            return new BuilderPattern(this);
        }

        public Builder bread(String bread) {
            this.bread = bread;
            return this;
        }

        public Builder dressing(String dressing) {
            this.dressing = dressing;
            return this;
        }

        public Builder meat(String meat) {
            this.meat = meat;
            return this;
        }
    }

    private final String bread;
    private final String meat;
    private final String dressing;

    private BuilderPattern(
            Builder b){
        this.bread = b.bread;
        this.meat = b.meat;
        this.dressing = b.dressing;
    }

    public String getBread() {
        return bread;
    }

    public String getMeat() {
        return meat;
    }

    public String getDressing() {
        return dressing;
    }
    
    public static void main(String[] args) {
        BuilderPattern.Builder b = new BuilderPattern.Builder();
        b.bread("b").dressing("d").meat("me");
        
        BuilderPattern bp = b.build();
    }
}
