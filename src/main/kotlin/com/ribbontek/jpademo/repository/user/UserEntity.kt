package com.ribbontek.jpademo.repository.user

import com.ribbontek.jpademo.repository.abstracts.AbstractEntityDelete
import jakarta.persistence.AttributeOverride
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType.LAZY
import jakarta.persistence.MapKeyJoinColumn
import jakarta.persistence.OneToMany
import jakarta.persistence.Table
import org.hibernate.annotations.SQLDelete

@Entity
@Table(name = "vw_user")
@AttributeOverride(name = "id", column = Column(name = "user_id"))
@SQLDelete(sql = "update jpademo.vw_user set deleted = true where user_id = ?")
data class UserEntity(
    @Column(nullable = false, length = 255)
    val email: String,
    @Column(nullable = false, length = 255)
    var firstName: String,
    @Column(nullable = false, length = 255)
    var lastName: String,
    @Column(nullable = false, length = 255)
    var idpUserName: String,
    @Column(nullable = false, length = 255)
    var idpStatus: String,
    @OneToMany(mappedBy = "user", fetch = LAZY)
    @MapKeyJoinColumn(name = "address_type_id")
    var addresses: MutableMap<AddressTypeEntity, UserAddressEntity>? = null
) : AbstractEntityDelete()
