using System;
using System.Collections.Generic;
using System.Data.Entity;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Wizard.Model.Entity;

namespace Wizard.Model
{
    public class WizardDbContext : DbContext
    {
        public WizardDbContext()
            : base("name=WizardDb")
        {
        }

        public DbSet<WizardUserInfo> WizardUserInfo { get; set; }
    }
}
