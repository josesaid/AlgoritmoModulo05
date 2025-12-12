package com.mx.development.said.microservice.clients.entity;

public enum ClientStatus {
    ACTIVE,        // Cliente habilitado
    INACTIVE,      // Cliente deshabilitado temporalmente
    PENDING,       // Falta verificación o aprobación
    SUSPENDED,     // Suspendido por reglas o incumplimiento
    BLOCKED,       // Bloqueado por seguridad o pagos
    BANNED,        // Expulsión permanente
    EXPIRED,       // Caducado (membresía, registro)
    DELETED,       // Eliminado (soft delete)
    ARCHIVED       // Archivado para uso histórico
}
