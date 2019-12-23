package study.datajpa.entities;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

/**
 * Created by finrir on 2019-12-13
 * Description:
 */
@MappedSuperclass
@Getter
@ToString(of = {"createdDate", "modifiedDate"})
public class JpaBaseEntity {
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @PrePersist // 엔티티가 저장되기 전에 미리 값을 할당
    public void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        this.createdDate = now;
        this.modifiedDate = now;
    }

    @PreUpdate  // 엔티티가 수정되기 전에 미리 값을 할당
    public void preUpdate() {
        this.modifiedDate = LocalDateTime.now();
    }

    @Column(name = "created_by")
    private String createdBy;
    @Column(name = "modified_by")
    private String modifiedBy;
}
