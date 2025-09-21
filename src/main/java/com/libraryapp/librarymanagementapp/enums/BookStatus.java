package com.libraryapp.librarymanagementapp.enums;

public enum BookStatus {
    AVAILABLE,   // ödünç alınabilir
    CHECKED_OUT, // ödünçte
    RESERVED,    // rezerve edilmiş
    LOST,        // kayıp
    DAMAGED,      // hasarlı
    INACTIVE
}