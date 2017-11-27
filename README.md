# ConductorMVP

[![Build Status](https://travis-ci.org/jshvarts/ConductorMVP.svg?branch=master)](https://travis-ci.org/jshvarts/ConductorMVP)

The source code in this repo accompanies my article [Creating Clean Architecture Multi-Project MVP App](http://proandroiddev.com/creating-clean-architecture-multi-project-mvp-app-34d753a187ad).

Here you will find a multi-project single Activity TODO app using [Conductor](https://github.com/bluelinelabs/Conductor) implementing MVP pattern (each `Controller` is a View).

## Main Libraries

* Conductor
* Room (with fully reactive data types)
* RxJava 2 and RxAndroid
* Dagger 2 with custom scopes
* ButterKnife for view binding

## Multi-Project setup

The app is configured as multi-project Gradle setup which improves build and testing speed in addition to providing clear isolation between the components of the app:

* **domain** module contains business logic/use-cases/repository interfaces as well as *Model*
* **data** module contains implementation of the **domain** layer abstractions
* **presentation** module contains Views and Presenters as well as Dependency Injection setup that glues all layers together

![Relationship between modules](docs/modules.png?raw=true)

This setup enables you to swap your backend implementation (for instance, migrate to Firebase or Realm) simply by replacing your **data** layer module and modifying a dependency in the **presentation** module. 
If the data layer changes, domain layer is not affected.

## Screenshots

![List of notes](docs/notes.png?raw=true)

![Note detail](docs/note_detail.png?raw=true)

![Edit a note](docs/edit_note.png?raw=true)

## Code Quality

This project aims to maintain high test coverage for all modules.

**Unit test code coverage is at 96%** (Views are excluded from unit tests since they are rather passive).

Main libraries used for testing are:
 
* JUnit
* Mockito-kotlin
* Hamcrest
* Jacoco

## License

    Copyright 2017 James Shvarts

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
