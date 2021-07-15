package controller.dao;


import model.Folder;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class FolderDAO extends DAO
{
    public FolderDAO()
    {
        super();
    }

    public Folder getFolder(String name) {
        return getEntityManager().find(Folder.class, name);
    }

    public List<Folder> readFolders()
    {
        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Folder> query = cb.createQuery(Folder.class);
        Root<Folder> root = query.from(Folder.class);
        query.select(root);

        return getEntityManager().createQuery(query).getResultList();
    }

    public void persistEntity(Folder folder)
    {
        getEntityManager().getTransaction().begin();
        getEntityManager().persist(folder);
        getEntityManager().getTransaction().commit();
    }
}
