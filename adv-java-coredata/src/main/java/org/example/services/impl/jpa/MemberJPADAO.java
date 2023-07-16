package org.example.services.impl.jpa;
import org.example.datamodel.Member;
import org.example.services.MemberDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class MemberJPADAO extends AbstractJPADAO<Member> implements MemberDAO {

    public MemberJPADAO(SessionFactory sf) {
        super(sf,Member.class);
    }

    @Override
    public List<Member> search(Member instance) {
        return null;
    }

    @Override
    public Member getById(int id) {
        return null;
    }

    @Override
    public List<Member> getAll() {
        return super.getAll();
    }
}
