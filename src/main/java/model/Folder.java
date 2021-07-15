package model;

import javax.persistence.*;

@Entity
@Table(name = "folder")

public class Folder
{
    @Id
    @Column(name = "folder_name")
    private String folderName;

    @Column(name = "aws_key")
    private String key;

    @Column(name="is_finished")
    private Boolean isFinished;

    public Folder() {}

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }
}
