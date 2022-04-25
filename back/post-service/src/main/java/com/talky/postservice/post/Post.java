package com.talky.postservice.post;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;
  private UUID author;
  private LocalDateTime createdAt;
  private String content;
  @Enumerated(EnumType.STRING)
  private PostPrivacy privacy;

  @ElementCollection
  @JoinTable(
    name = "post_assets",
    joinColumns = @JoinColumn(name = "post_id")
  )
  @Column(name = "asset_id")
  private List<String> assets;

  public Post(UUID author, String content, PostPrivacy privacy, List<String> assets) {
    this.author = author;
    this.content = content;
    this.privacy = privacy;
    this.assets = assets;

    this.id = UUID.randomUUID();
    this.createdAt = LocalDateTime.now();
  }
}
