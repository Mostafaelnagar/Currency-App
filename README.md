<h1 align="center">
Android Currency App
</h1>


:point_right: Clean Architecture:
-----------------
<div align="center">
<img src="https://github.com/Mostafaelnagar/Currency-App/blob/master/architecure/architecture.png">
</div>


:point_right: Domain & Data Layer:
-----------------
<div align="center">
<img src="https://github.com/Mostafaelnagar/Currency-App/blob/master/architecure/data_layer.png">
</div>


:point_right: Presentation Layer:
-----------------
<div align="center">
<img src="https://github.com/Mostafaelnagar/Currency-App/blob/master/architecure/ui_layer.png">
</div>


:point_right: Architecture:
-----------------

- Following Clean Architecture.
- MVVM Architecture.
- Repository pattern.
- Use Cases.
- Applying SOLID principles, each class has a single job with separation of concerns by making
  classes independent of each other and communicating with interfaces.
- Using Kotlin-KTS & buildSrc to handle project dependencies.

:point_right: Tech Stack & Libraries:
-----------------

- Navigation component - navigation graph for navigating and replacing screens/fragments
- DataBinding - allows to more easily write code that interacts with views and
  replaces ```findViewById```.
- ViewModel - UI related data holder, lifecycle aware.
- Flow & StateFlow - Build data objects that notify views when the underlying database changes.
- Dagger-Hilt for dependency injection. Object creation and scoping is handled by Hilt.
- Kotlin Coroutines - for managing background threads with simplified code and reducing needs for
  callbacks
- Retrofit2 & OkHttp3 - to make REST requests to the web service integrated.
- Room database - for storing data.
- Data store - for saving small data in storage and replaces ```SharedPreferences```

:point_right: Project Structure:
-----------------

- Features required in App :
- convert currencies from different bases

<div align="center">
<img src="https://github.com/Mostafaelnagar/Currency-App/blob/master/images/convertion%20page.jpg">
</div>

:point_right: Code Style:
-----------

- Following official kotlin code style

:point_right: Apply Git Hooks:
-----------

- To apply git hooks in order to automate process of styling and checking your code, just follow
  this steps:
  - Copy ```pre-commit``` file depending on your OS from ```myGitHooks```.
  - Paste it into ```.git/hooks``` in your project.
- Now each time you commit your changes, ```ktlintFormat``` and  ```ktlintCheck``` will
  automatically run

:point_right: Local Development:
-----------

- Here are some useful Gradle commands for executing this example:
  - `./gradlew clean` - Deletes build directory.

