using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Web;
using System.Web.Services;
using System.Web.Services.Protocols;

namespace Wizard.Webservice
{
    /// <summary>
    /// WebService1 的摘要说明
    /// </summary>
    [WebService(Namespace = "http://system.soa.application.axis2.wizard.com")]
    [WebServiceBinding(ConformsTo = WsiProfiles.BasicProfile1_1)]
    [System.ComponentModel.ToolboxItem(false)]
    [SoapDocumentService(RoutingStyle = SoapServiceRoutingStyle.RequestElement)]
    // 若要允许使用 ASP.NET AJAX 从脚本中调用此 Web 服务，请取消注释以下行。 
    // [System.Web.Script.Services.ScriptService]
    public class WizardWs : System.Web.Services.WebService
    {

        [WebMethod]
        public bool login(string username, string password)
        {
            if ("admin" == username && "111" == password)
                return true;
            return false;
        }

        [WebMethod]
        public bool logout(string username)
        {
            return false;
        }

        [WebMethod]
        public FileInfo GetFile()
        {
            FileInfo file = new FileInfo(@"C:\Users\wizard\Desktop");
            return file;
        }

    }
}
