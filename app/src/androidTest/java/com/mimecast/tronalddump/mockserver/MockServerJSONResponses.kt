package com.mimecast.tronalddump.mockserver

class MockServerJSONResponses {

    object SUCCESS {
        const val TAGS = "{\"count\":54,\"total\":54,\"_embedded\":[\"Hillary Clinton\",\"Ted Cruz\",\"George Pataki\",\"Scott Walker\",\"Tim Kaine\",\"Rand Paul\",\"Donald Trump\",\"George W. Bush\",\"Megyn Kelly\",\"Women\",\"Bobby Jindal\",\"Martin O'Malley\",\"Tom Ridge\",\"Michael Nutter\",\"Barack Obama\",\"Mexico\",\"Immigrants\",\"Neil Young\",\"History\",\"Bill and Hillary Clinton\",\"Money\",\"Bette Midler\"],\"_links\":{\"self\":{\"href\":\"\\/tag\"}}}"
        const val TAG_DETAILS = "{\"count\":1,\"total\":277,\"_embedded\":{\"tags\":[{\"appeared_at\":\"2016-07-30T19:55:26\",\"created_at\":\"2016-11-20T01:32:07.085780\",\"quote_id\":\"dxo0LpDbRlOIHs630rMK9A\",\"tags\":[\"Hillary Clinton\"],\"updated_at\":\"2016-11-20T01:32:07.085780\",\"value\":\"Why doesn't the media want to report that on the two \\\"Big Thursdays\\\" when Crooked Hillary and I made our speeches - Republican's won ratings\",\"_links\":{\"self\":{\"href\":\"\\/quote\\/dxo0LpDbRlOIHs630rMK9A\"}},\"_embedded\":{\"author\":[{\"created_at\":\"2016-11-14T01:14:02.096776\",\"bio\":null,\"author_id\":\"wVE8Y7BoRKCBkxs1JkqAvw\",\"name\":\"Donald Trump\",\"slug\":\"donald-trump\",\"updated_at\":\"2016-11-14T01:14:02.096776\"}],\"source\":[{\"created_at\":\"2016-11-20T01:32:06.695089\",\"filename\":null,\"quote_source_id\":\"GQPWKpGvQEurAep0gwwXxA\",\"remarks\":null,\"updated_at\":\"2016-11-20T01:32:06.695089\",\"url\":\"https:\\/\\/twitter.com\\/realDonaldTrump\\/status\\/759477522232901632\"}]}}]},\"_links\":{\"self\":{\"href\":\"\\/tag\\/Hillary%20Clinton?page=2\"},\"prev\":{\"href\":\"\\/tag\\/Hillary%20Clinton\"},\"next\":{\"href\":\"\\/tag\\/Hillary%20Clinton?page=3\"},\"first\":{\"href\":\"\\/tag\\/Hillary%20Clinton\"},\"last\":{\"href\":\"\\/tag\\/Hillary%20Clinton?page=12\"}}}"
    }

    object FAIL {
        // General errors
        const val NOT_FOUND = "{\"status\":404,\"message\":\"No route found\"}"
    }
}