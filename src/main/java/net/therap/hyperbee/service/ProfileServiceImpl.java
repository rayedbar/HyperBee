package net.therap.hyperbee.service;

import net.therap.hyperbee.dao.UserDao;
import net.therap.hyperbee.domain.Profile;
import net.therap.hyperbee.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author duity
 * @since 11/22/16.
 */
@Service
public class ProfileServiceImpl implements ProfileService {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UserDao userDao;

    @Override
    public Profile findProfileById(int id) {
        return em.find(Profile.class, id);
    }

    @Override
    @Transactional
    public String saveProfileForUser(Profile profile, int userId) {
        User user=userDao.findById(userId);
        em.persist(profile);
        user.setProfile(profile);
        em.persist(user);
        em.flush();

        return "profile is saved in user";
    }
}
