using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Wizard.Model.Entity
{
    [Table("t_wizard_user_info")]
    public class WizardUserInfo
    {
        [Key, Required, MaxLength(36), Column("PK_ID", TypeName = "nvarchar")]
        public virtual string PkId { get; set; }

        [Required, MaxLength(20), Column("USERNAME", TypeName = "nvarchar")]
        public virtual string Username { get; set; }

        [Required, MaxLength(80), Column("PASSWORD", TypeName = "nvarchar")]
        public virtual string Password { get; set; }

        [MaxLength(300), Column("USER_DETAIL", TypeName = "nvarchar")]
        public virtual string UserDetail { get; set; }
    }
}
