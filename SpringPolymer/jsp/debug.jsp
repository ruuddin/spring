<!DOCTYPE html>
<html>
<head>
	<script src="http://polygit.org/components/webcomponentsjs/webcomponents-lite.js"></script>
	<link rel="import" href="../polymerelements/elements.html" />
    <title>Monitor Console</title>
</head>
	<body unresolved>
	    Coming from MessageController: ${message} <br><br>
		<hello-element></hello-element> <br><br>
	    
	    <!-- Polymer Type extension example -->
	    <input type="text" is="input-element" ></input><br><br>
	    <!-- data binding -->
	    <alert-element></alert-element><br><br>
	    <!-- Object data binding -->
	    <object-data-binding></object-data-binding><br><br>
	    <!-- Attribute binding -->
	    <attribute-binding></attribute-binding><br><br>
	    <!-- Computed Property -->
	    <computed-property></computed-property><br><br>
	    <!-- Observer demo -->
	    <observer-demo></observer-demo><br><br>
	    <conditional-template></conditional-template><br><br>
	    <template-repeater></template-repeater><br><br>
		<!-- Auto binding template: helps to use data binding featuer without defining a component -->
		<template id="autoBind" is="dom-bind">
			Auto-binding template
			<template is="dom-repeat" items="{{data}}">
				<p>{{item}}</p>
			</template>
		</template>
		<dom-manipulate></dom-manipulate>
		<content-tag>
			<ul>
				<li class="alert-message">Content-tag1</li>
				<li class="alert-message">Content-tag1</li>
				<li class="alert-message">Content-tag1</li>
			</ul>
		</content-tag>
		<content-tag>
			<span class=".alert-head-content">Head Content</span>
			<span class=".alert-data">Alert data</span>
		</content-tag>
	</body>
</html>