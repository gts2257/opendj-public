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
 *      Copyright 2006-2008 Sun Microsystems, Inc.
 *      Portions Copyright 2014-2015 ForgeRock AS
 */
package org.opends.server.types;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.forgerock.opendj.ldap.ByteString;
import org.forgerock.opendj.ldap.ConditionResult;

/**
 * This class defines a data structure for storing and interacting
 * with an attribute that may be used in the Directory Server.
 * <p>
 * Attributes are immutable and therefore any attempts to modify them
 * will result in an {@link UnsupportedOperationException}.
 * <p>
 * There are two types of attribute: real attributes and virtual attributes.
 * Real attributes can be created using the {@link AttributeBuilder} class
 * or by using the various static factory methods in the {@link Attributes} class,
 * whereas virtual attributes are represented using the {@link VirtualAttribute} class.
 * New attribute implementations can be implemented by either implementing this interface
 * or by extending {@link AbstractAttribute}.
 */
@org.opends.server.types.PublicAPI(
    stability = org.opends.server.types.StabilityLevel.UNCOMMITTED,
    mayInstantiate = false,
    mayExtend = false,
    mayInvoke = true)
public interface Attribute extends Iterable<ByteString>
{
  /**
   * Indicates whether this attribute has any value(s) that are
   * approximately equal to the provided value.
   *
   * @param assertionValue
   *          The assertion value for which to make the determination.
   * @return {@link ConditionResult#UNDEFINED} if this attribute does not have
   *         an approximate matching rule, {@link ConditionResult#TRUE} if at
   *         least one value is approximately equal to the provided
   *         value, or {@link ConditionResult#FALSE} otherwise.
   */
  ConditionResult approximatelyEqualTo(ByteString assertionValue);

  /**
   * Indicates whether this attribute contains the specified value.
   *
   * @param value
   *          The value for which to make the determination.
   * @return {@code true} if this attribute has the specified
   *         value, or {@code false} if not.
   */
  boolean contains(ByteString value);

  /**
   * Indicates whether this attribute contains all the values in the
   * collection.
   *
   * @param values
   *          The set of values for which to make the determination.
   * @return {@code true} if this attribute contains all the
   *         values in the provided collection, or {@code false}
   *         if it does not contain at least one of them.
   */
  boolean containsAll(Collection<ByteString> values);

  /**
   * Indicates whether this attribute matches the specified assertion value.
   *
   * @param assertionValue
   *          The assertion value for which to make the determination.
   * @return {@code true} if this attribute matches the specified assertion
   *         value, or {@code false} if not.
   */
  ConditionResult matchesEqualityAssertion(ByteString assertionValue);

  /**
   * Indicates whether the provided object is an attribute that is
   * equal to this attribute. It will be considered equal if the
   * attribute type, set of values, and set of options are equal.
   *
   * @param o
   *          The object for which to make the determination.
   * @return {@code true} if the provided object is an
   *         attribute that is equal to this attribute, or
   *         {@code false} if not.
   */
  @Override
  boolean equals(Object o);

  /**
   * Retrieves the attribute type for this attribute.
   *
   * @return The attribute type for this attribute.
   */
  AttributeType getAttributeType();

  /**
   * Retrieves the user-provided name for this attribute.
   *
   * @return The user-provided name for this attribute.
   */
  String getName();

  /**
   * Retrieves the user-provided name of this attribute, along with
   * any options that might have been provided.
   *
   * @return The user-provided name of this attribute, along with any
   *         options that might have been provided.
   */
  String getNameWithOptions();

  /**
   * Retrieves the unmodifiable set of attribute options for this
   * attribute. The returned set of options are not normalized.
   *
   * @return The unmodifiable set of attribute options for this
   *         attribute.
   */
  Set<String> getOptions();

  /**
   * Indicates whether this attribute has any value(s) that are
   * greater than or equal to the provided value.
   *
   * @param assertionValue
   *          The assertion value for which to make the determination.
   * @return {@link ConditionResult#UNDEFINED} if this attribute does not have
   *         an ordering matching rule, {@link ConditionResult#TRUE} if at
   *         least one value is greater than or equal to the provided
   *         assertion value, or {@link ConditionResult#FALSE} otherwise.
   */
  ConditionResult greaterThanOrEqualTo(ByteString assertionValue);

  /**
   * Indicates whether this attribute has all of the options in the provided collection.
   *
   * @param options
   *          The collection of options for which to make the determination (may be {@code null}).
   * @return {@code true} if this attribute has all of the specified options,
   *         or {@code false} if it does not have at least one of them.
   */
  boolean hasAllOptions(Collection<String> options);

  /**
   * Retrieves the hash code for this attribute. It will be calculated
   * as the sum of the hash code for the attribute type and all values.
   *
   * @return The hash code for this attribute.
   */
  @Override
  int hashCode();

  /**
   * Indicates whether this attribute has the specified option.
   *
   * @param option
   *          The option for which to make the determination.
   * @return {@code true} if this attribute has the specified option,
   *         or {@code false} if not.
   */
  boolean hasOption(String option);

  /**
   * Indicates whether this attribute has any options at all.
   *
   * @return {@code true} if this attribute has at least one
   *         option, or {@code false} if not.
   */
  boolean hasOptions();

  /**
   * Returns {@code true} if this attribute contains no
   * attribute values.
   *
   * @return {@code true} if this attribute contains no
   *         attribute values.
   */
  boolean isEmpty();

  /**
   * Indicates whether this is a real attribute (persisted) rather than a virtual attribute
   * (dynamically computed).
   *
   * @return {@code true} if this is a real attribute.
   */
  boolean isReal();

  /**
   * Indicates whether this is a virtual attribute (dynamically computed) rather than a real
   * attribute (persisted).
   *
   * @return {@code true} if this is a virtual attribute.
   */
  boolean isVirtual();

  /**
   * Returns an iterator over the attribute values in this attribute.
   * The attribute values are returned in the order in which they were
   * added this attribute. The returned iterator does not support
   * attribute value removals via {@link Iterator#remove()}.
   *
   * @return An iterator over the attribute values in this attribute.
   */
  @Override
  Iterator<ByteString> iterator();

  /**
   * Indicates whether this attribute has any value(s) that are less
   * than or equal to the provided value.
   *
   * @param assertionValue
   *          The assertion value for which to make the determination.
   * @return {@link ConditionResult#UNDEFINED} if this attribute does not have
   *         an ordering matching rule, {@link ConditionResult#TRUE} if at
   *         least one value is less than or equal to the provided
   *         assertion value, or {@link ConditionResult#FALSE} otherwise.
   */
  ConditionResult lessThanOrEqualTo(ByteString assertionValue);

  /**
   * Indicates whether this attribute has any value(s) that match the
   * provided substring.
   *
   * @param subInitial
   *          The subInitial component to use in the determination.
   * @param subAny
   *          The subAny components to use in the determination.
   * @param subFinal
   *          The subFinal component to use in the determination.
   * @return {@link ConditionResult#UNDEFINED} if this attribute does not have
   *         a substring matching rule, {@link ConditionResult#TRUE} if at
   *         least one value matches the provided substring, or
   *         {@link ConditionResult#FALSE} otherwise.
   */
  ConditionResult matchesSubstring(ByteString subInitial,
      List<ByteString> subAny, ByteString subFinal);

  /**
   * Indicates whether this attribute has exactly the specified set of
   * options.
   *
   * @param options
   *          The set of options for which to make the determination
   *          (may be {@code null}).
   * @return {@code true} if this attribute has exactly the
   *         specified set of options.
   */
  boolean optionsEqual(Set<String> options);

  /**
   * Returns the number of attribute values in this attribute.
   *
   * @return The number of attribute values in this attribute.
   */
  int size();

  /**
   * Retrieves a one-line string representation of this attribute.
   *
   * @return A one-line string representation of this attribute.
   */
  @Override
  String toString();

  /**
   * Appends a one-line string representation of this attribute to the
   * provided buffer.
   *
   * @param buffer
   *          The buffer to which the information should be appended.
   */
  void toString(StringBuilder buffer);
}
