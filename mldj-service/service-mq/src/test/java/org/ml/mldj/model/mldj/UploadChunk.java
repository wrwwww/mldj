package org.ml.mldj.model.mldj;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "upload_chunk", schema = "hxds_mis")
public class UploadChunk {
    @Id
    @Size(max = 50)
    @Column(name = "id", nullable = false, length = 50)
    private String id;

    @Size(max = 255)
    @NotNull
    @Column(name = "file_md5", nullable = false)
    private String fileMd5;

    @Size(max = 255)
    @NotNull
    @Column(name = "chunk_size", nullable = false)
    private String chunkSize;

    @NotNull
    @Column(name = "part_number", nullable = false)
    private Integer partNumber;

    @NotNull
    @Lob
    @Column(name = "file_size", nullable = false)
    private String fileSize;

    @NotNull
    @Lob
    @Column(name = "total_chunk", nullable = false)
    private String totalChunk;

    @Size(max = 1)
    @Column(name = "upload_status", length = 1)
    private String uploadStatus;

    @Size(max = 255)
    @Column(name = "file_name")
    private String fileName;

    @Size(max = 10)
    @Column(name = "file_ext", length = 10)
    private String fileExt;

}