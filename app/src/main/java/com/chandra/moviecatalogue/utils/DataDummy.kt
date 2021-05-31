package com.chandra.moviecatalogue.utils

import com.chandra.moviecatalogue.data.model.ListPopularMovieResponse
import com.chandra.moviecatalogue.data.model.ListPopularTVResponse
import com.chandra.moviecatalogue.data.model.detailmovie.DetailMovieResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.DetailTVSeriesResponse
import com.chandra.moviecatalogue.data.model.detailtvseries.Genre


object DataDummy {

    fun generateDummyMovies(): DetailMovieResponse =
        DetailMovieResponse(
            id = 399566,
            title = "Godzilla vs. Kong",
            overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
            genres = listOf(
                Genre(1231, "Action"),
                Genre(2, "Science Fiction"),
                Genre(3, "Drama"),
                Genre(4, "Comedy")
            ),
            releaseDate = "2021-03-24",
            posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
            voteAverage = 8.9

        )


    fun generateDummySeries(): DetailTVSeriesResponse =
        DetailTVSeriesResponse(
            firstAirDate = "2021-03-19",
            name = "The Falcon and the Winter Soldier",
            id = 88396,
            overview = "Following the events of “Avengers: Endgame”, the Falcon, Sam Wilson and the Winter Soldier, Bucky Barnes team up in a global adventure that tests their abilities, and their patience.",
            genres = listOf(Genre(1231, "comedy")),
            posterPath = "https://image.tmdb.org/t/p/w200/6kbAMLteGO8yyewYau6bJ683sw7.jpg",
                    voteAverage = 8.9
        )


    fun generateDummyListMovies(): List<ListPopularMovieResponse> {
        val movies = ArrayList<ListPopularMovieResponse>()

        movies.add(
            ListPopularMovieResponse(
                id = 399566,
                title = "Godzilla vs. Kong",
                overview = "In a time when monsters walk the Earth, humanity’s fight for its future sets Godzilla and Kong on a collision course that will see the two most powerful forces of nature on the planet collide in a spectacular battle for the ages.",
                releaseDate = "2021-03-24",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                2,
                title = "Mortal Engine",
                overview = "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                releaseDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                3,
                title = "Alita: Battle Angle",
                overview = "When Alita awakens with no memories of who she is in a future world she doesn't know, she is captured by Ido, a compassionate doctor who realizes that somewhere within the shell of this abandoned cyborg is the heart and soul of a young woman with an extraordinary. . ",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                4,
                title = "Fantastic Beasts: The Crimes of Grindelwald",
                overview = "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                5,
                title = "Cold Pursuit",
                overview = "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                6,
                title = "How to Train Your Dragon: The Hidden World",
                overview = "When Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless' discovery of an untested and elusive partner drives Night Fury away. As danger increases in Hiccup's home and reign as village head is put to the test, both the dragon and the rider must make an impossible decision. to save their kind. ",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                7,
                title = "Avengers: Infinity War",
                overview = "As the Avengers and his allies continue to protect the world from threats too great for a single hero to handle, a new danger has emerged from the cosmic shadow: Thanos. An intergalactic blasphemer despot.",

                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                8,
                title = "Robin Hood",
                overview = "A war-hardened Crusader and his Moorish commander mount an audacious revolt against the corrupt English crown.",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                9,
                title = "Ralph Breaks the Internet",
                overview = "Video game bad guy Ralph and fellow misfit Vanellope von Schweetz must risk it all by traveling to the World Wide Web in search of a replacement part to save Vanellope's video game, Sugar Rush. In way over their heads, Ralph and Vanellope rely on the citizens of the internet - the netizens - to help navigate their way, including an entrepreneur named Yesss, who is the head algorithm and the heart and soul of trend-making site BuzzzTube. ",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        movies.add(
            ListPopularMovieResponse(
                10,
                title = "Spider-Main: Into the Spider-Verse",
                overview = "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                releaseDate = "2019",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )


        return movies
    }

    fun generateDummyListSeries(): List<ListPopularTVResponse> {
        val series = ArrayList<ListPopularTVResponse>()

        series.add(
            ListPopularTVResponse(
                id = 399566,
                name = "The Arrow",
                overview = "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                firstAirDate = "2021-03-24",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 2,
                name = "The Walking Dead",
                overview = "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 3,
                name = "Family Guy",
                overview = "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 4,
                name = "The Flash",
                overview = "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only 'meta-human' who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                firstAirDate = "2014",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 5,
                name = "Gotham",
                overview = "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 6,
                name = "Supergirl",
                overview = "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 7,
                name = "The Umbrella Academy",
                overview = "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 8,
                name = "Naruto Shippuden",
                overview = "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 9,
                name = "NCIS",
                overview = "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )
        series.add(
            ListPopularTVResponse(
                id = 10,
                name = "The Simpsons",
                overview = "Set in Springfield, the average American town, the show focuses on the antics and everyday adventures of the Simpson family; Homer, Marge, Bart, Lisa and Maggie, as well as a virtual cast of thousands. Since the beginning, the series has been a pop culture icon, attracting hundreds of celebrities to guest star. The show has also made name for itself in its fearless satirical take on politics, media and American life in general.",
                firstAirDate = "2018",
                posterPath = "https://image.tmdb.org/t/p/w200/pgqgaUx1cJb5oZQQ5v0tNARCeBp.jpg",
                voteAverage = 0.0
            )
        )

        return series
    }
}
