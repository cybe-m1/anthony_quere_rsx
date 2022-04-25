package com.talky.userservice.devices;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

interface DeviceRepository extends JpaRepository<Device, String> {
  Set<Device> findByUserId(UUID user);
}
