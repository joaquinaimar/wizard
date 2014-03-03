using Microsoft.Practices.Unity;

namespace Wizard.Model.Service
{

    public class ServiceBase
    {
        [Dependency]
        public WizardDbContext dbContext { get; set; }
    }
}
