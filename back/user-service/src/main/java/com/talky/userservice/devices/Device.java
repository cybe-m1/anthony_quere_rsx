package com.talky.userservice.devices;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "t_device")
class Device {

  @Id
  private String deviceId;

  private UUID userId;

  public Device(String deviceId, UUID userId) {
    this.deviceId = deviceId;
    this.userId = userId;
  }
}
