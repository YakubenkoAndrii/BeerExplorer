Beer Explorer app
==================

app. It is a **work in progress** 🚧.

**Beer Explorer** is a Android app built entirely with Kotlin and Jetpack Compose. It follows Android design and development best practices.

# Features

**Beer Explorer** displays content from the
[Beer API](https://api.punkapi.com/v2/). Users can see the breed of beer and found something new. Especially strong beer has a special label
and won't let you miss interesting )

# Architecture

The **Beer Explorer** app follows the 
[official architecture guidance](https://developer.android.com/topic/architecture)
and is described in detail in the
[architecture learning journey](docs/ArchitectureLearningJourney.md).

# Modularization

The **Beer Explorer** app has been fully modularized for the faster build time. Also developers are able to work with each feature closely,
and don't afraid to broke another part's of application.

# Build

The **Beer Explorer** app contains the usual `debug` and `release` build variants. `release` isn't finished, proguard is empty.
App contains ktlint code checker, for running it, follow the 'Terminal -> ./gradlew --continue ktlintCheck'

# Testing

To facilitate testing of components, **Now in Android** uses dependency injection with
[Hilt](https://developer.android.com/training/dependency-injection/hilt-android).

Most data layer components are defined as interfaces.
Then, concrete implementations (with various dependencies) are bound to provide those interfaces to
other components in the app. Thanks to interfaces, developers are able to add different implementations and libs for mocking.

The project contains Unit tests for the viewModels and repositories.

# UI

App UI created with Jetpack Compose. Also app supporting a light and dark modes.

# Might to improve if have a more time

- integration/UI tests
- more beautiful design, animations (would be cool to get pro. design, figma or zeplin mock). Material 3 dynamic color theme
- UI optimization, because Jetpack Compose is bringing new features with new versions of libraries every week and there is always things to improve
- integrate and figuring out plugin for detecting and drop unusable dependencies
