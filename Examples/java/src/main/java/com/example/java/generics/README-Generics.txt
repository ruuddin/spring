Bounded Type parameters: 
	GenericBox.calculateA - Only classes that extend N or implement N are accepted. Notice how the types are declared <U extends N, N>
	GenericBox.calculateB - Methods can be invoked on the upper bound type.
	GenericBox.multipleBounds - Generic type with multipleBounds. Class type MUST appear first, followed by interface.
Wildcard ? represents an unknown type.
	can be used as type of parameter, field or local variable
	cannot be used as type argument for generic method invocation, generic class instance creation or supertype
	Upper bounded: public static void process(List<? extends Foo> list){ .. } - matches Foo and its subtypes
	unbounded: 
	lower bounded: public static void addNumbers(List<? super Integer> list){ ... }