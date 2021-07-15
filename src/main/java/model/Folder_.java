package model;


import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Folder.class)
public class Folder_
{
    public static SingularAttribute<Folder, String> folderName;
    public static SingularAttribute<Folder, String> key;
    public static SingularAttribute<Folder, Boolean> isFinished;
}
