SELECT S.Time, M.Title, T.TheatreName
FROM MOVIE AS M, SHOWTIME AS S, THEATRE AS T
WHERE M.MovieID = S.MovieID AND T.TheatreID = S.TheatreID and M.MovieID = 11 and T.TheatreID = 2