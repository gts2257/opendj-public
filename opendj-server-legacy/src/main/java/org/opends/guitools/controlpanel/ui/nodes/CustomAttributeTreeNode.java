/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License, Version 1.0 only
 * (the "License").  You may not use this file except in compliance
 * with the License.
 *
 * You can obtain a copy of the license at legal-notices/CDDLv1_0.txt
 * or http://forgerock.org/license/CDDLv1.0.html.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at legal-notices/CDDLv1_0.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information:
 *      Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 *
 *
 *      Copyright 2008 Sun Microsystems, Inc.
 */

package org.opends.guitools.controlpanel.ui.nodes;

import org.opends.server.types.AttributeType;

/**
 * Class of the nodes that represent a custom attribute in the 'Manage
 * Schema' tree.
 *
 */
public class CustomAttributeTreeNode extends SchemaElementTreeNode
{
  private static final long serialVersionUID = -9198486156950262507L;
  private AttributeType attr;

  /**
   * Constructor of the node.
   * @param name the name of the node.
   * @param attr the attribute definition.
   */
  public CustomAttributeTreeNode(String name, AttributeType attr)
  {
    super(name, attr);
    this.attr = attr;
  }

  /**
   * Returns the definition of the attribute represented by this node.
   * @return the definition of the attribute represented by this node.
   */
  public AttributeType getAttribute()
  {
    return attr;
  }
}
