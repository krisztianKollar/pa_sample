package com.codecool.web.service.simple;

import com.codecool.web.dao.TrackDao;
import com.codecool.web.dao.UserDao;
import com.codecool.web.model.Track;
import com.codecool.web.model.User;
import com.codecool.web.service.LoginService;
import com.codecool.web.service.exception.ServiceException;

import java.sql.SQLException;
import java.util.List;

public final class SimpleTrackService {

    private final TrackDao trackDao;

    public SimpleTrackService(TrackDao trackDao) {
        this.trackDao = trackDao;
    }

    public List<Track> getTracks() throws SQLException{
        return trackDao.findAllTrack();
    }

}
