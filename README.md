# Scheduler

This project was built as a skill test.

In this README I will explain some of the decision I made in regards to the project and architecture.

## Setup

### Requirements
- JDK 11
- Android Studio Arctic Fox or higher

### Instructions

Clone this repo, import it on Android studio and run the project or install it via gradle command line
```
./gradlew installDebug
```

## Usage

The first screen when you open the app is a list of office spaces that were derived from the given JSON,
you can see the booking in each of them by tapping their images. 
On the 'scheduler' screen you will be able to see the booking in *Local time*. This means that the bookings
in GMT timezones will appear as GMT (even though we're now in BST) because the dates are prior to summer time change.
The office in east coast will show the times in EST. 

## Architecture

This app follows the standard android Model-View-ViewModel as per Google's guidelines.

It follows some key aspects of the android clean architecture:
- Business layer is in the UseCases, and not spread throughout the app.
- Views are simple. Only taking care of showing the data that is given to it.
- Repository layer that hides networking and database implementations.
- Dependency injection.
- Modularization, app module only depends on core and doesn't know about the other feature modules.

### Libraries

### Android
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture)
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle)
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel)
    - [Navigation](https://developer.android.com/guide/navigation/navigation-getting-started)
    - [Compose](https://developer.android.com/jetpack/compose)

### Third-party
- [Koin](https://insert-koin.io/)
- [Timber](https://github.com/JakeWharton/timber)
- [Coil](https://coil-kt.github.io/coil/)
- [Retrofit](https://square.github.io/retrofit/)
- [Moshi](https://github.com/square/moshi)

#### Quality check
- [klint](https://github.com/shyiko/ktlint)
- [detekt](https://github.com/arturbosch/detekt)

#### Tests
- [Mockk](https://github.com/mockk/mockk)
- [Turbine](https://github.com/cashapp/turbine/)

### Decisions on the libraries

For dependency injection I decided against Dagger/Hilt because it's not worth for a small and simple
app like this.
Since this is a small project for skill demonstration I'd rather use some other dependency injection with easier
setup and use. Therefore I opted to use Koin, a lightweight library that allowed me to use DI principles in the app.
It's not as fast as Dagger and doesn't have compile-time checks, but it's good enough for this use case. Also, it's built
in Kotlin which provides better usage with the language.

For testing, I'd rather use MockK instead of Mockito for the same reason: it's built for Kotlin and therefore it's better to use.
Also, it provides coroutine support which is something that Mockito doesn't. For testing [StateFlow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/)
I opted for using Turbine, a library made by Square that facilitates testing hot flows.

The quality tools are good for checking code style (Ktlint), and also finding potential bugs and performance problems (detekt).
They're important to maintain a consistent code base even with different developers on the team. Only issue is that
detekt rules doesn't match well with Compose coding guidelines, therefore I opted for suppressing the issues
in Compose files.

## Modularization

I opted to modularise the app as much as I thought was necessary due to the short development time.
The modularization allows incremental build times as a change in one module doesn't require the other modules to compile as well.
It gives more flexibility during development as well since it modules could be distributed to the developers, avoiding conflicts.
Also, it's more organized and forces better coding practices.

Apart from the core module, the "features" could be almost plug-and-play. Allowing easier and faster development and maintenance.

# Remarks

I wasn't so sure how to best represent time, if I should've used
`ZonedDateTime` instead of `LocalDateTime`, but for the purposes of this exercise `LocalDateTime` works fine.
I had to enable Android's R8 Desugar to use Java 8 `Time` which contains JSR-310.

I took a lot longer than it was expected for this project (closer to 16h I would say) that's why I saw this
as an opportunity to take a second look at Flow and Compose which are subjects that I don't have much experience.
If I were to do this using XML Views, it would've taken me less than half the time. 

I enjoyed building this  using Compose but I spent lot of time on small issues that I didn't understand what was going on. 
More experience would've definitely remedied this. Also I refactored my view model 3 times I think, I went from StateFlow back
to regular flow then to StateFlow again because I didn't think carefully about my requirements. For example
when I needed to access the office & its calendar entries in the second page I had to resort to StateFlow again
to keep it alive in the ViewModel. Another option would be to setup something like a REDUX pattern that would've
kept the loaded data in memory.

## What else I would do

- I would definitely implement instrumented and UI tests. However I didn't have enough time to do it.
- I would add JaCoCo for test coverage and break builds if the coverage is under a threshold.
- I would add some form of Crashlytics (Firebase, Sentry or Raygun) to ensure crashes in production are known and taken care of.
- Some form of analytics to track app usage.
- Better UI/UX

## Closing Thoughts

Some of my decisions cost me a lot of time. 

Initially I decided to do a local web server on my machine
and have the Android Emulator access it through a proxy. So I setup a server and I thought it would be too much
work for you when running the app, so I changed to use a local `MockWebServer` instead. I could've just
returned the JSON directly from a class acting as the API access, but I decided by properly setting up
Retrofit and a web server to "do the right thing" which was totally unnecessary.

I don't mind the time spent personally, but in a real development scenario that could meant a) product is delayed
or b) overtime. So one has to be very careful when approaching certain challenges to not waste time.

Using compose was fun, but certainly my lack of experience with it cost me time and the UI looks worse because of it.
I would've definitely completed it faster in XML but I wanted to try compose out since the UI would be simple enough.

Anyway, at least architecture wise I feel like it's worth spending some time on it. It prepares the app for future
growth if it's done correctly. Otherwise we could complete the app in 1 hour or less if we didn't care about it.
It's a balance between having a well built app or a messy MVP. Software can always be improved given more time.
