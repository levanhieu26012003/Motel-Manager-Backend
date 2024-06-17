/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.lvh.pojo;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author levan
 */
@Entity
@Table(name = "motel")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Motel.findAll", query = "SELECT m FROM Motel m"),
    @NamedQuery(name = "Motel.findById", query = "SELECT m FROM Motel m WHERE m.id = :id"),
    @NamedQuery(name = "Motel.findByActive", query = "SELECT m FROM Motel m WHERE m.active = :active"),
    @NamedQuery(name = "Motel.findByCreatedDate", query = "SELECT m FROM Motel m WHERE m.createdDate = :createdDate"),
    @NamedQuery(name = "Motel.findByUpdatedDate", query = "SELECT m FROM Motel m WHERE m.updatedDate = :updatedDate"),
    @NamedQuery(name = "Motel.findByTitle", query = "SELECT m FROM Motel m WHERE m.title = :title"),
    @NamedQuery(name = "Motel.findByAddress", query = "SELECT m FROM Motel m WHERE m.address = :address"),
    @NamedQuery(name = "Motel.findByPrice", query = "SELECT m FROM Motel m WHERE m.price = :price"),
    @NamedQuery(name = "Motel.findByArea", query = "SELECT m FROM Motel m WHERE m.area = :area"),
    @NamedQuery(name = "Motel.findByNumberTenant", query = "SELECT m FROM Motel m WHERE m.numberTenant = :numberTenant"),
    @NamedQuery(name = "Motel.findByWards", query = "SELECT m FROM Motel m WHERE m.wards = :wards"),
    @NamedQuery(name = "Motel.findByDistrict", query = "SELECT m FROM Motel m WHERE m.district = :district"),
    @NamedQuery(name = "Motel.findByProvince", query = "SELECT m FROM Motel m WHERE m.province = :province"),
    @NamedQuery(name = "Motel.findByStatus", query = "SELECT m FROM Motel m WHERE m.status = :status")})
public class Motel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "active")
    private Boolean active;
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "address")
    private String address;
    @Column(name = "price")
    private Integer price;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "area")
    private Float area;
    @Column(name = "number_tenant")
    private Integer numberTenant;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "wards")
    private String wards;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "district")
    private String district;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "province")
    private String province;
    @Size(max = 20)
    @Column(name = "status")
    private String status;
    @OneToMany(mappedBy = "motelId")
    private Collection<Image> imageCollection;
    @OneToMany(mappedBy = "houseId")
    private Collection<Comment> commentCollection;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    
    @Transient
    private List<MultipartFile> files;

    public Motel() {
    }

    public Motel(Long id) {
        this.id = id;
    }

    public Motel(Long id, String title, String address, String wards, String district, String province) {
        this.id = id;
        this.title = title;
        this.address = address;
        this.wards = wards;
        this.district = district;
        this.province = province;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getNumberTenant() {
        return numberTenant;
    }

    public void setNumberTenant(Integer numberTenant) {
        this.numberTenant = numberTenant;
    }

    public String getWards() {
        return wards;
    }

    public void setWards(String wards) {
        this.wards = wards;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @XmlTransient
    public Collection<Image> getImageCollection() {
        return imageCollection;
    }

    public void setImageCollection(Collection<Image> imageCollection) {
        this.imageCollection = imageCollection;
    }

    @XmlTransient
    public Collection<Comment> getCommentCollection() {
        return commentCollection;
    }

    public void setCommentCollection(Collection<Comment> commentCollection) {
        this.commentCollection = commentCollection;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Motel)) {
            return false;
        }
        Motel other = (Motel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lvh.pojo.Motel[ id=" + id + " ]";
    }
    
     @PrePersist
    protected void onCreate() {
        createdDate = new Date();
        updatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    /**
     * @return the files
     */
    public List<MultipartFile> getFiles() {
        return files;
    }

    /**
     * @param files the files to set
     */
    public void setFiles(List<MultipartFile> files) {
        this.files = files;
    }
    
}
