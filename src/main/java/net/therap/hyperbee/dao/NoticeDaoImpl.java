package net.therap.hyperbee.dao;

import net.therap.hyperbee.domain.Notice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author rumman
 * @since 11/22/16
 */
@Repository
public class NoticeDaoImpl implements NoticeDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void saveOrUpdate(Notice notice) {
        if (notice.isNew()) {
            em.persist(notice);
            em.flush();
        } else {
            em.merge(notice);
        }
    }

    @Override
    public Notice findById(int noticeId) {
        return em.find(Notice.class, noticeId);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Notice> findAll() {
        return em.createNamedQuery("Notice.findAllNotice", Notice.class)
                .getResultList();
    }

    @Override
    public List<Notice> findLatestNotices(int range) {
        return em.createNamedQuery("Notice.findLatestNotices", Notice.class)
                .setMaxResults(range)
                .getResultList();
    }

    @Override
    @Transactional
    public void delete(Notice notice) {
        delete(notice.getId());
    }

    @Override
    @Transactional
    public void delete(int noticeId) {
        Notice attachedDish = em.getReference(Notice.class, noticeId);
        em.remove(attachedDish);
    }
}