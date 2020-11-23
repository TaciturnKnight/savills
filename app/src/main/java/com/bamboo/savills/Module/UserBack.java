package com.bamboo.savills.Module;

import com.google.gson.annotations.SerializedName;

public class UserBack {

    @SerializedName("@odata.context")
    private String _$OdataContext138; // FIXME check this code
    private String createdDateTime;
    private String description;
    private String id;
    private String lastModifiedDateTime;
    private String name;
    private String webUrl;
    private String driveType;
    private CreatedByBean createdBy;
    private LastModifiedByBean lastModifiedBy;
    private OwnerBean owner;
    private QuotaBean quota;

    public String get_$OdataContext138() {
        return _$OdataContext138;
    }

    public void set_$OdataContext138(String _$OdataContext138) {
        this._$OdataContext138 = _$OdataContext138;
    }

    public String getCreatedDateTime() {
        return createdDateTime;
    }

    public void setCreatedDateTime(String createdDateTime) {
        this.createdDateTime = createdDateTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime;
    }

    public void setLastModifiedDateTime(String lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebUrl() {
        return webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public CreatedByBean getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(CreatedByBean createdBy) {
        this.createdBy = createdBy;
    }

    public LastModifiedByBean getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(LastModifiedByBean lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public OwnerBean getOwner() {
        return owner;
    }

    public void setOwner(OwnerBean owner) {
        this.owner = owner;
    }

    public QuotaBean getQuota() {
        return quota;
    }

    public void setQuota(QuotaBean quota) {
        this.quota = quota;
    }

    public static class CreatedByBean {
        /**
         * user : {"displayName":"系统帐户"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * displayName : 系统帐户
             */

            private String displayName;

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }
        }
    }

    public static class LastModifiedByBean {
        /**
         * user : {"email":"sunny.qiao@bamboonetworks.com","id":"ffbeda94-4305-479a-8411-e8872b73d51e","displayName":"Sunny Qiao"}
         */

        private UserBeanX user;

        public UserBeanX getUser() {
            return user;
        }

        public void setUser(UserBeanX user) {
            this.user = user;
        }

        public static class UserBeanX {
            /**
             * email : sunny.qiao@bamboonetworks.com
             * id : ffbeda94-4305-479a-8411-e8872b73d51e
             * displayName : Sunny Qiao
             */

            private String email;
            private String id;
            private String displayName;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }
        }
    }

    public static class OwnerBean {
        /**
         * user : {"email":"sunny.qiao@bamboonetworks.com","id":"ffbeda94-4305-479a-8411-e8872b73d51e","displayName":"Sunny Qiao"}
         */

        private UserBeanXX user;

        public UserBeanXX getUser() {
            return user;
        }

        public void setUser(UserBeanXX user) {
            this.user = user;
        }

        public static class UserBeanXX {
            /**
             * email : sunny.qiao@bamboonetworks.com
             * id : ffbeda94-4305-479a-8411-e8872b73d51e
             * displayName : Sunny Qiao
             */

            private String email;
            private String id;
            private String displayName;

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getDisplayName() {
                return displayName;
            }

            public void setDisplayName(String displayName) {
                this.displayName = displayName;
            }
        }
    }

    public static class QuotaBean {
        /**
         * deleted : 0
         * remaining : 1099495012065
         * state : normal
         * total : 1099511627776
         * used : 14426400
         */

        private int deleted;
        private long remaining;
        private String state;
        private long total;
        private int used;

        public int getDeleted() {
            return deleted;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        public long getRemaining() {
            return remaining;
        }

        public void setRemaining(long remaining) {
            this.remaining = remaining;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public int getUsed() {
            return used;
        }

        public void setUsed(int used) {
            this.used = used;
        }
    }
}
